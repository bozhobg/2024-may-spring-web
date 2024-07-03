package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.entity.Model;

public interface ModelService {

    boolean existsById(Long id);

    Model getModelById(Long id);
}
