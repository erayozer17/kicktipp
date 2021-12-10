package com.erayozer.kicktipp.model;

import java.io.Serializable;

public interface FirebaseModel extends Serializable {
    String collectionName(); // collection name
    String identifier(); // document is saved under this value
}
