package com.dh.student.service;


import com.dh.student.event.MetricStudentCourseProducer;
import com.dh.student.model.Student;
import com.dh.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final MetricStudentCourseProducer metricStudentCourseProducer;

    public StudentService(StudentRepository studentRepository, MetricStudentCourseProducer metricStudentCourseProducer) {
        this.studentRepository = studentRepository;
        this.metricStudentCourseProducer = metricStudentCourseProducer;
    }

    public void save(Student student) {
        studentRepository.save(student);
    }


    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }


    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public void update(Student Student) {
        if (studentRepository.existsById(Student.getStudentId())) {
            studentRepository.save(Student);
        }
    }

    public String getMetricsCourse(Long studentId) {
        String operationId = UUID.randomUUID().toString();
        metricStudentCourseProducer.sendMessage(new MetricStudentCourseProducer.MetricStudentCourseData(studentId,operationId));
        return operationId;
    }
}
