package lec09.collectionexample;

import javax.persistence.*;
import java.util.List;

@Entity
public class ExamEntity {
    public ExamEntity() {
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "exam_collection_id")
    private List<ExamCollectionEntity> examCollectionEntities;
}
