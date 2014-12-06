import play.*;
import models.AuxGlobal;
import models.dao.GenericDAO;
import play.db.jpa.JPA;

import java.lang.System;

public class Global extends GlobalSettings {

    public void onStart(Application app) {

        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                AuxGlobal.read();
            }


        });

    }

    public void onStop(Application app) {
    }

}

