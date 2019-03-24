package ru.spring.mongo.listener;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.spring.mongo.domain.Genre;

@Component
@AllArgsConstructor
public class GenreEventListener extends AbstractMongoEventListener<Genre> {

    private final MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Genre> event) {
        event.getSource().setId(null);
        super.onBeforeConvert(event);
    }

//    @Override
//    public void onBeforeSave(BeforeSaveEvent<Genre> event) {
//        String sqlId = event.getSource().getId();
//        Query query = new Query();
//        query.addCriteria(where("name").is("genre").and("sqlId").is(sqlId));
//        BingingIds bingingIds = mongoOperations.findOne(query, BingingIds.class);
//        if (bingingIds == null) {
//            event.getSource().setId(null);
//            bingingIds.setSqlId(sqlId);
//            mongoOperations.save(bingingIds);
//        }
//        super.onBeforeSave(event);
//    }
//
//    @Override
//    public void onAfterSave(AfterSaveEvent<Genre> event) {
//        super.onAfterSave(event);
//    }
}
