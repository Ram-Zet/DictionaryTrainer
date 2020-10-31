package ramzet89.dictionary.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
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