package monster;

public interface AbstractFactory<E,V, T> {
    E create(V o, T t);
}
