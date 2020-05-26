package erik.wiesi.handler;

import erik.wiesi.model.Entity;

public abstract class Handler {

    public static void death(Entity entity) {
        entity = null;
    }

}
