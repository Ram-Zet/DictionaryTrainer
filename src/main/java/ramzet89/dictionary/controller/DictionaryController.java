package ramzet89.dictionary.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ramzet89.dictionary.model.request.AddWordsRequest;
import ramzet89.dictionary.model.response.AddWordsResponse;

@RestController
@RequestMapping("/api/v1/words")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('user:add_words')")
public class DictionaryController {
    public ResponseEntity<AddWordsResponse> addWords(@RequestBody AddWordsRequest request) {
        
    }
}
