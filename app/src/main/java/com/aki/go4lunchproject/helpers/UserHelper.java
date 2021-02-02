package com.aki.go4lunchproject.helpers;

import com.aki.go4lunchproject.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserHelper {

    public static final String COLLECTION_NAME = "users";

    public static CollectionReference getUserCollection() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    public static Task<Void> createUser(String uid, String username, String urlPicture) {
        User userToCreate = new User(uid, username, urlPicture);
        return UserHelper.getUserCollection()
                .document(uid)
                .set(userToCreate);
    }

    public static Task<DocumentSnapshot> getUser(String uid) {
        return UserHelper.getUserCollection()
                .document(uid)
                .get();
    }

    public static Task<Void> updateUsername(String username, String uid) {
        return UserHelper.getUserCollection().document(uid).update("username", username);
    }

    public static Task<Void> updateHasBooked(Boolean hasBooked, String uid) {
        return UserHelper.getUserCollection().document(uid).update("hasBooked", hasBooked);
    }

    public static Task<Void> deleteUser(String uid) {
        return UserHelper.getUserCollection().document(uid).delete();
    }

}
