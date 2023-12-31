const admin = require('firebase-admin');
const serviceAccount = require('/Users/junseok/Documents/server/veggie-neighbors-3df82-firebase-adminsdk-jwcks-4b3226dbd8.json');

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});

const db = admin.firestore();
const collectionRef = db.collection('FridgePosts'); // Firestore 컬렉션 참조

collectionRef.orderBy('timestamp', 'desc').limit(1).get()
  .then((querySnapshot) => {
    if (!querySnapshot.empty) {
      const docSnapshot = querySnapshot.docs[0];
      const fridgeItem = docSnapshot.data();
      const name = fridgeItem.date;

      // FCM 푸시 알림 보내기
      const payload = {
        notification: {
          title: '배달이 오고 있어요!',
          body: `배송일입니다. 까먹지 마세요!: ${name}`
        },
      };

      // 특정 토크 또는 토큰에 알림을 보냅니다.
      return admin.messaging().sendToTopic('all', payload);
    } else {
      console.log('No documents found');
    }
  })
  .then((response) => {
    console.log('Successfully sent message:', response);
  })
  .catch((error) => {
    console.log('Error sending message:', error);
  });
