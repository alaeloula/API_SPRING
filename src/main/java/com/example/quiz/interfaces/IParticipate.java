package com.example.quiz.interfaces;




import com.example.quiz.dto.ParticipateDTOreq;
import com.example.quiz.dto.ParticipateDTOres;
import com.example.quiz.dto.SallonDTOres;
import com.example.quiz.model.ParticipateID;

import java.util.List;

public interface IParticipate {
    List<ParticipateDTOres> findAll();
    ParticipateDTOres findById(ParticipateID id);
    ParticipateDTOres save(ParticipateDTOreq participateDTOreq);
    ParticipateDTOres deleteById(ParticipateID id);
    ParticipateDTOres update(ParticipateDTOreq participateDTOreq);
    List<SallonDTOres> findParticipatesByStudent(Integer studentId, String status);
}
