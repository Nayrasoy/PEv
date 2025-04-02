package algorithm.cross;

import java.util.List;

import model.Individuo;

public abstract class CrossMethod {

    public abstract <T> List<Individuo> cross(Individuo individuo1, Individuo individuo2);

    public abstract CrossType getType();

    public abstract CrossMethod create();

}
