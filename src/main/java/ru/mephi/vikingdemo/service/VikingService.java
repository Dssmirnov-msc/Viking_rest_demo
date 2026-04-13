package ru.mephi.vikingdemo.service;

import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.Viking;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class VikingService {
    // каждый раз при изменении создаётся новая копия списка 
    private final CopyOnWriteArrayList<Viking> vikings = new CopyOnWriteArrayList<>();
    private final VikingFactory vikingFactory;

    @Autowired
    public VikingService(VikingFactory vikingFactory) {
        this.vikingFactory = vikingFactory;
    }

    public List<Viking> findAll() {
        return List.copyOf(vikings);
    }

    public Viking createRandomViking() {
        Viking viking = vikingFactory.createRandomViking();
        vikings.add(viking);
        return viking;
    }

    public void addViking(Viking viking) {
        vikings.add(viking);
    }

    public void deleteViking(String vikingName) {
        vikings.removeIf(viking -> viking.name().equals(vikingName));
    }

    public Viking findViking(String vikingName) {
        for (Viking viking : vikings) {
            if (viking.name().equals(vikingName)) {
                return viking;
            }
        }
        return null;
    }

    public void updateViking(Viking viking) {
        for (Viking targetViking : vikings) {
            if (targetViking.name().equals(viking.name())) {
                vikings.remove(targetViking);
                vikings.add(viking);
            }
        }
    }
}
