package ramzet89.dictionary.mapper;

import org.mapstruct.Mapper;
import ramzet89.dictionary.db.entity.DictionaryEntity;
import ramzet89.dictionary.model.DictionaryRaw;

@Mapper(componentModel = "spring")
public interface DictionaryMapper {
    DictionaryRaw mapToRaw(DictionaryEntity dictionaryEntity);
}
