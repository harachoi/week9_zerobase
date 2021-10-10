package com.example.zerobase;

import com.example.zerobase.domain.ZerobaseCourse;
import com.example.zerobase.domain.ZerobaseCourseRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class Homework {
    private final ZerobaseCourseRepository repository;

    // TODO: 테스트가 통과할 수 있도록 아래 메서드 들을 작성하세요.

    public Optional<ZerobaseCourse> getZerobaseCourse(Long id) {
        // TODO: id 가 일치하며, hidden = false 인 강의만 조회되어야 함

        ZerobaseCourse course = repository.findById(id);

        if (course != null) {
            if (!course.isHidden())
                return Optional.of(course);
        }

        return Optional.empty();
    }

    public List<ZerobaseCourse> getZerobaseCourse(String status) {
        // TODO: status가 일치하고, hidden = false 인 강의들이 조회되어야 함
        List<ZerobaseCourse> list = repository.findAll();
        if (list == null) {
            return null;
        }

        List<ZerobaseCourse> courseList = new ArrayList<>();
        for (ZerobaseCourse x : list) {
            if (x.getStatus().equals(status) && !x.isHidden()) {
                courseList.add(x);
            }
        }

        return courseList;
    }

    public List<ZerobaseCourse> getOpenZerobaseCourse(LocalDate targetDt) {
        // TODO: status = "OPEN" 이고, hidden = false 이며,
        // startAt <= targetDt && targetDt <= endAt 인 강의만 조회되어야함.
        List<ZerobaseCourse> list = repository.findAll();
        if (list == null) {
            return null;
        }

        List<ZerobaseCourse> courseList = new ArrayList<>();
        for (ZerobaseCourse x : list) {
            if (x.getStatus().equals("OPEN") && !x.isHidden() && x.getStartAt().isBefore(targetDt)
                    && x.getEndAt().isAfter(targetDt)) {
                courseList.add(x);
            }
        }

        return courseList;
    }
}
