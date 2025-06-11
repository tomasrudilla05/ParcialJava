package com.excusas.prontuario;

import java.util.ArrayList;
import java.util.List;


public abstract class ObservableBase {
    private List<Observer> observers = new ArrayList<>();

    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }

    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }

    protected void notificarObservers(Prontuario prontuario) {
        for (Observer observer : observers) {
            observer.actualizar(prontuario);
        }
    }
}
