package ramzet89.dictionary.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@Table(schema = "application", name = "dictionary")
public class DictionaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String english;

    @Column(nullable = false)
    private String russian;

    private String russian2;

    private String russian3;

    private String transcription;

    private Integer attempts = 0;

    private Integer failures = 0;

    @Column(name = "attempt_date")
    private LocalDateTime attemptDate;

    @Column(name = "difficulty_coefficient")
    private Double difficultyCoefficient;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DictionaryEntity that = (DictionaryEntity) o;
        return english.equals(that.english);
    }

    @Override
    public int hashCode() {
        return Objects.hash(english);
    }
}
