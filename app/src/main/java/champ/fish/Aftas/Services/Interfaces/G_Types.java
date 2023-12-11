package champ.fish.Aftas.Services.Interfaces;

import java.util.List;

public interface G_Types<Q, S, I> {
    S create(Q q);

    S update(Q q);

    Boolean delete(I id);

    S get(I i);

    List<S> getAll();
}
