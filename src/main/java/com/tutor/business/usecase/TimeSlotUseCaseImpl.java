package com.tutor.business.usecase;

import com.tutor.business.dto.SessionDto;
import com.tutor.business.dto.TimeSlotDto;
import com.tutor.business.dto.TutorTimeSlotResponseDto;
import com.tutor.business.service.SessionService;
import com.tutor.business.service.TimeSlotService;
import com.tutor.common.dto.ResponseDataModel;
import com.tutor.common.dto.SearchRequest;
import com.tutor.controller.request.TimeSlotRequest;
import com.tutor.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeSlotUseCaseImpl implements TimeSlotUseCase {

    private final TimeSlotService timeSlotService;
    private final SessionService sessionService;

    @Override
    public ResponseDataModel<TimeSlotDto> findAll(SearchRequest searchRequest) {
        return timeSlotService.findAll(searchRequest);
    }

    @Override
    public TimeSlotDto findById(Long id) {
        return timeSlotService.findById(id);
    }

    @Override
    public List<TimeSlotDto> findByTutorId(Long tutorId) {
        return timeSlotService.findByTutorId(tutorId);
    }

    @Override
    public List<TutorTimeSlotResponseDto> findByTutorIdWithDates(Long tutorId) {
        List<TimeSlotDto> timeSlots = timeSlotService.findByTutorId(tutorId);
        List<TutorTimeSlotResponseDto> response = new ArrayList<>();
        
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate endDate = tomorrow.plusDays(6); // 7 days total including tomorrow

        List<SessionDto> sessions = sessionService.findByTutorIdAndDateBetween(tutorId, tomorrow, endDate);
        
        // Create a set of "reserved" slots (date + startTime)
        Set<String> reservedSlots = sessions.stream()
                .map(session -> session.getDate().toString() + "_" + session.getStartTime().toString())
                .collect(Collectors.toSet());

        for (int i = 0; i < 7; i++) {
            LocalDate date = tomorrow.plusDays(i);
            int dayOfWeekValue = date.getDayOfWeek().getValue(); // 1=Monday, ..., 7=Sunday

            for (TimeSlotDto ts : timeSlots) {
                // Assuming ts.getDayOfWeek() stores 1 for Monday, ..., 7 for Sunday as per requirement
                if (ts.getDayOfWeek() != null && ts.getDayOfWeek() == dayOfWeekValue) {
                    String slotKey = date.toString() + "_" + ts.getStartTime().toString();

                    if (!reservedSlots.contains(slotKey)) {
                        TutorTimeSlotResponseDto dto = new TutorTimeSlotResponseDto();
                        dto.setId(ts.getId());
                        dto.setDate(date);
                        dto.setStartTime(ts.getStartTime());
                        dto.setEndTime(ts.getEndTime());
                        dto.setDayOfWeek(ts.getDayOfWeek());
                        dto.setTutorId(ts.getTutorId());
                        response.add(dto);
                    }
                }
            }
        }
        return response;
    }

    @Override
    public List<TimeSlotDto> create(List<TimeSlotRequest> timeSlotRequests, AppUserDetails userDetails) {
        return timeSlotService.create(timeSlotRequests, userDetails);
    }

    @Override
    public TimeSlotDto update(Long id, TimeSlotDto timeSlotDto) {
        return timeSlotService.update(id, timeSlotDto);
    }

    @Override
    public void delete(Long id) {
        timeSlotService.delete(id);
    }
}
