package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.services.s3.model.S3Object;

public class S3Interaction {

	private static final String clientRegion = "us-east-1";
	private static final String bucketName = "dart-rstead-bucket/media";
	private static final String apiLocation = "https://s3.amazonaws.com/";
	private static final AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion)
			.withCredentials(new ProfileCredentialsProvider()).build();

	public static String putMedia(File input, String contentType) {
		String keyName = UUID.randomUUID().toString();

		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(contentType);
			PutObjectRequest request = new PutObjectRequest(bucketName, keyName, input);
			request.setMetadata(metadata);
			s3Client.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
		return apiLocation + bucketName + "/" + keyName;
	}

	public static Boolean deleteMedia(String fileUrl) {
		try {
			String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
			s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
			return true;
		} catch (AmazonServiceException e) {
			e.printStackTrace();
			return false;
		} catch (SdkClientException e) {
			e.printStackTrace();
			return false;
		}
	}
}
