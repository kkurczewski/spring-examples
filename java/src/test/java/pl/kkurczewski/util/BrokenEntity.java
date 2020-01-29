package pl.kkurczewski.util;

import javax.persistence.Entity;

@Entity
public class BrokenEntity {
    // if test is not properly isolated this class will throw exception:
    // org.hibernate.AnnotationException: No identifier specified for entity: [...]
}