package ramzet89.dictionary.mapper;

import org.mapstruct.Mapper;
import ramzet89.dictionary.db.entity.DictionaryEntity;
import ramzet89.dictionary.model.response.GetWordstoLearnResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DictionaryMapper {

    List<GetWordstoLearnResponse.DictionaryRecord> toDictionaryRecordList(List<DictionaryEntity> dictionaryEntityList);

    GetWordstoLearnResponse.DictionaryRecord toDictionaryRecord(DictionaryEntity dictionaryEntity);
}
