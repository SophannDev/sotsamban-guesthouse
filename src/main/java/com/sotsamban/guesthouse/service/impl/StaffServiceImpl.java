package com.sotsamban.guesthouse.service.impl;

import com.sotsamban.guesthouse.common.StatusCode;
import com.sotsamban.guesthouse.domain.staff.Staff;
import com.sotsamban.guesthouse.domain.staff.StaffRepository;
import com.sotsamban.guesthouse.dto.request.staff.StaffRequest;
import com.sotsamban.guesthouse.dto.response.staff.StaffResponse;
import com.sotsamban.guesthouse.exception.BusinessException;
import com.sotsamban.guesthouse.service.StaffService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public void createStaff(StaffRequest request) {

        // check email is exist
        var existsEmail = staffRepository.emailExists(request.getEmail());
        if (existsEmail) {
            throw new BusinessException(StatusCode.EMAIL_ALREADY_EXISTS);
        }

        Staff staff = new Staff();
        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setEmail(request.getEmail());
        staff.setPhone(request.getPhone());
        staff.setPosition(request.getPosition());
        staff.setSalary(request.getSalary());

        staffRepository.save(staff);
    }

    @Override
    public StaffResponse findStaffById(Long id) {
        var findStaffId = staffRepository.findStaffById(id)
                .orElseThrow(() -> new BusinessException(StatusCode.NOT_FOUND));

        return StaffResponse.builder()
                .staffId(findStaffId.getStaffId())
                .firstName(findStaffId.getFirstName())
                .lastName(findStaffId.getLastName())
                .email(findStaffId.getEmail())
                .phone(findStaffId.getPhone())
                .position(findStaffId.getPosition())
                .salary(findStaffId.getSalary())
                .createdAt(findStaffId.getCreatedAt())
                .updatedAt(findStaffId.getUpdatedAt())
                .build();
    }
}
