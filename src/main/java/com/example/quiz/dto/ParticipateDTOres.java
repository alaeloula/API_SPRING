package com.example.quiz.dto;


import com.example.quiz.model.ParticipateID;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class ParticipateDTOres {
    @NonNull
    private ParticipateID participateID;
    @NonNull
    private LocalDate dateParticipation;
}
