package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.StudioService;
import br.com.golden_awards_worst_movies.domain.model.Studio;
import br.com.golden_awards_worst_movies.domain.repository.StudioRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class StudioServiceImpl implements StudioService {
    final StudioRepositoryI studioRepositoryI;

    @Autowired
    public StudioServiceImpl(StudioRepositoryI studioRepositoryI) {
        this.studioRepositoryI = studioRepositoryI;
    }

    @Override
    public List<Studio> getExistingAndNewStudios(List<Studio> studios) {
        List<Studio> existingStudios = studioRepositoryI.findByNameIn(studios.stream().map(Studio::getName).collect(Collectors.toList()));
        List<Studio> newStudiosNames = studios.stream()
                .filter(studio -> existingStudios.stream().noneMatch(
                        s -> s.getName().equalsIgnoreCase(studio.getName())
                )).toList();
//        List<Studio> newStudios = newStudiosNames.stream().map(
//                studioRepositoryI::save).toList();
        List<Studio> allStudios = new ArrayList<>(existingStudios);
        allStudios.addAll(newStudiosNames);
        return allStudios;
    }
}
