package champ.fish.Aftas.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public final class Competition {
    @Id
    @Column(unique = true, nullable = false)
    private String code;

    @ManyToMany(mappedBy = "competitions")
    private Set<Member> members = new HashSet<>();

    @NotNull
    private LocalDate date;

    @NotNull
    @FutureOrPresent
    private LocalDateTime startTime;

    @NotNull
    @FutureOrPresent
    private LocalDateTime endTime;

    @NotNull
    @Min(value = 0)
    private Integer numberOfParticipants;

    @NonNull
    private String location;

    @NonNull
    @Min(value = 0)
    private Float amount;

    @NonNull
    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;

    @NonNull
    @OneToMany(mappedBy = "competition")
    private List<Hunting> huntingList;
}
