package cz.jalasoft.psaninastroji.application;

import cz.jalasoft.psaninastroji.domain.model.EvaluationResult;
import cz.jalasoft.psaninastroji.domain.model.EvaluationService;
import cz.jalasoft.psaninastroji.domain.model.InputText;
import cz.jalasoft.psaninastroji.domain.model.excercise.Excercise;
import cz.jalasoft.psaninastroji.domain.model.excercise.ExcerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jan Lastovicka
 * @since 09/10/2019
 */
@Component
public class ApplicationService {

    private final ExcerciseRepository repository;
    private final EvaluationService evaluationService;

    @Autowired
    public ApplicationService(ExcerciseRepository repository, EvaluationService evaluationService) {
        this.repository = repository;
        this.evaluationService = evaluationService;
    }

    public Excercise excerciseByNumber(int number) {
        return repository.byNumber(number);
    }

    public EvaluationResult validate(int number, InputText input) {
        Excercise excercise = repository.byNumber(number);
        EvaluationResult result = evaluationService.evaluate(excercise.pattern(), input);
        return result;
    }
}
