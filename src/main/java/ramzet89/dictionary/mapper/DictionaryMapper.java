package ramzet89.dictionary.mapper;

import org.mapstruct.Mapper;
import ramzet89.dictionary.db.entity.DictionaryEntity;
import ramzet89.dictionary.model.commons.DictionaryRecord;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DictionaryMapper {

    List<DictionaryRecord> toDictionaryRecordList(List<DictionaryEntity> dictionaryEntityList);

    DictionaryRecord toDictionaryRecord(DictionaryEntity dictionaryEntity);
}
