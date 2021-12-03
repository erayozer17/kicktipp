package com.erayozer.kicktipp.service;

import com.erayozer.kicktipp.model.FirebaseModel;
import com.erayozer.kicktipp.model.Team;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.core.GenericTypeResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public abstract class FirebaseCrud<T extends FirebaseModel> {
    private static Firestore dbFirestore;
    private final Class<T> genericType;
    private T type;

    @SuppressWarnings("unchecked")
    public FirebaseCrud() throws InstantiationException, IllegalAccessException {
        this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), FirebaseCrud.class);
        if (this.type == null) {
            assert this.genericType != null;
            this.type = this.genericType.newInstance();
        }
    }

    public Firestore getDbFirestore(){
        if (dbFirestore == null){
            dbFirestore = FirestoreClient.getFirestore();
        }
        return dbFirestore;
    }

    public String updateObject(T t, String documentName) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = getDbFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(type.collectionName()).document(documentName).set(t);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public List<Map<String, Object>> getAllObjects() throws InterruptedException, ExecutionException {
        List<Map<String, Object>> teams = new ArrayList<>();
        Firestore dbFirestore = getDbFirestore();
        CollectionReference collectionReference = dbFirestore.collection(type.collectionName());
        ApiFuture<QuerySnapshot> future = collectionReference.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot q : documents) {
            teams.add(q.getData());
        }

        return teams;
    }

    public T getObject(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = getDbFirestore();
        DocumentReference documentReference = dbFirestore.collection(type.collectionName()).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        T t = null;

        if(document.exists()) {
            t = document.toObject(genericType);
        }
        return t;
    }

    public String deleteObject(String name) {
        Firestore dbFirestore = getDbFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(type.collectionName()).document(name).delete();
        return "Document with Patient ID "+name+" has been deleted";
    }

    public String saveObject(T t, String documentName) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = getDbFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.
                collection(type.collectionName()).
                document(documentName).
                set(t);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public abstract String save(T t) throws InterruptedException, ExecutionException;
    public abstract T get(String name) throws InterruptedException, ExecutionException;
    public abstract List<Map<String, Object>> getAll() throws InterruptedException, ExecutionException;
    public abstract String update(T t) throws InterruptedException, ExecutionException;
    public abstract String delete(String name);
}
