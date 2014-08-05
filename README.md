AurusPhotoGallery
=================

Instagram Sample Application

Basic workflow:

User can login to Instagram using there api.
Then all Images inside their media will be fetched and arrange in grid view. User can click on any image to enlarge it.



To use
Instagram Sample application, follow bellow steps

Create package "com.aurus.instagram" in your application. Copy .java files from the specific folder of this zip folder to above package.

Create package "com.aurus.images" in your application. Copy .java files from the specific folder of this zip folder to above package.

Create package "com.aurus.photogallery.sampleapp" in your application. Copy .java files from the specific folder of this zip folder to above package.

Define activities in your AndroidManifest.xml (AurusPhotoGallery, ImageGridView and ShowFullImage)

Define Instagram object with call back method for auth as shown below
instaObj = new InstagramApp(this, CLIENT_ID, CLIENT_SECRET, CALLBACK_URL); instaObj.setListener(oathListener);

For user authentication on Instagram, you need to call below code
instaObj.authorize();

This will give Ui where you need to enter user name and password for Instagram. After completion of this you will have details about user such as Userid and UserName.

To load image from media for the above user, you need to call

FetchImageAsync imgAsync = new FetchImageAsync(instaObj, AurusPhotoGallery.this); imgAsync.execute();

This will call media url of Instagram and fetch all the image with “thumb in background for specific user. Once the fetching is complete it will call activity (ImageGridView) to arrange them in grid view. If you click on any image this will create another activity (ShowFullImage) and show image in full screen.



Issue:
Use instagram api to fetch tags by selfie.
