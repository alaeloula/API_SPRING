package com.example.quiz.repository;

import com.example.quiz.model.Participate;
import com.example.quiz.model.ParticipateID;
import com.example.quiz.model.Sallon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipateRepository extends JpaRepository<Participate, ParticipateID> {
    @Query("SELECT p.participateID.room FROM Participate p WHERE p.participateID.student.id = :studentId")
    List<Sallon> findParticipatesByStudentId(@Param("studentId") Integer studentId);

    @Query("SELECT p.participateID.room FROM Participate p WHERE p.participateID.room.id NOT IN (SELECT par.participateID.room FROM Participate par WHERE par.participateID.student.id = :studentId)")
    List<Sallon> findParticipatesByStudentNotIn(@Param("studentId") Integer studentId);

}
