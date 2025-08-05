package com.sotsamban.guesthouse.service;

import com.sotsamban.guesthouse.dto.request.staff.StaffRequest;
import com.sotsamban.guesthouse.dto.response.staff.StaffResponse;

public interface StaffService {

    void createStaff(StaffRequest request);

    StaffResponse findStaffById(Long id);

}
