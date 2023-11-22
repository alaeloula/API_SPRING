package com.example.quiz.service;

import com.example.quiz.dto.StudentDTO;
import com.example.quiz.interfaces.IStudent;
import com.example.quiz.model.Student;
import com.example.quiz.repository.StudentRepository;
import com.example.quiz.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudent {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Page<StudentDTO> findAll(Pageable pageable) {
        Page<Student> students = studentRepository.findAll(pageable);
        return students.map(student -> modelMapper.map(student, StudentDTO.class));
    }

    @Override
    public StudentDTO findById(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        Student student= modelMapper.map(studentDTO, Student.class);
        studentRepository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO deleteById(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        studentRepository.deleteById(id);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        Student student = studentRepository.findById(studentDTO.getCode())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + studentDTO.getCode()));
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setAdresse(studentDTO.getAdresse());
        student.setDateInscription(studentDTO.getDateInscription());
        studentRepository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }
}
