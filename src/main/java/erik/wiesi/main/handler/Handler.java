package erik.wiesi.main.handler;

import erik.wiesi.model.entities.Entity;

public abstract class Handler {

    public static void death(Entity entity) {
        entity = null;
    }

}
