package com.example.neck_is_turtle;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraProvider;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class BottomSheetCamera extends BottomSheetDialogFragment implements CallbackInterface{
    private static final String TAG = "BottomSheetCamera";
    //초기변수 설정
    private View view;
    private Button btn_down,btn_upload,btn_cancel,btn_change;
    // 카메라 프리뷰
    private TextureView mTextureView;
    private PreView mPreview;
    static final int REQUEST_CAMERA = 1; // 후면 카메라 , 0: 전면 카메라
    // 사진 찍기
    private ImageButton btn_take;
    // 사진 저장하기
    MediaScannerConnection mediaScannerConnection = null;
    MediaScannerConnection.MediaScannerConnectionClient mMediaScannerClient = null;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bottom_sheet_camera,container,false);

        mTextureView = view.findViewById(R.id.CameraView);
        mPreview = new PreView(this.getContext(), mTextureView);
        mPreview.setOnCallbackListener(this);

        // 다시찍기 버튼
        btn_cancel = view.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreview.onPause();
                mPreview.openCamera();
            }
        });

        btn_change = view.findViewById(R.id.btn_change);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_change.setSelected(!btn_change.isSelected());
                if(btn_change.isSelected()){
                    mPreview.setCameraId("1");
                }
                else{
                    mPreview.setCameraId("0");
                }
            }
        });

        btn_upload = view.findViewById(R.id.btn_upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이미지뷰에 mPreview 캡쳐사진 올리기

                // 서버에 이미지 올리기 parameter(UserKey, Date, Image)

            }
        });

        btn_take = view.findViewById(R.id.btn_take);
        btn_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사진찍기
                mPreview.takePicture();
                // 찍힌 사진 프리퍼런스에 임시 저장해두기
                // PreView에서 메소드 구현
            }
        });

        btn_down = view.findViewById(R.id.btn_down);
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPreview.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPreview.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CAMERA:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (permission.equals(Manifest.permission.CAMERA)) {
                        if(grantResult == PackageManager.PERMISSION_GRANTED) {
                            mTextureView = (TextureView) view.findViewById(R.id.CameraView);
                            mPreview = new PreView(this.getContext(), mTextureView);
                            Log.d(TAG,"mPreview set");
                        } else {
                            Log.e(TAG,"Should have camera permission to run");
                            dismiss();
                        }
                    }
                }
                break;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialog;
                setupRatio(bottomSheetDialog);
            }
        });
        return  dialog;
    }

    private void setupRatio(BottomSheetDialog bottomSheetDialog) {
        //id = com.google.android.material.R.id.design_bottom_sheet for Material Components
        //id = android.support.design.R.id.design_bottom_sheet for support librares
        FrameLayout bottomSheet = (FrameLayout)
                bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();
        layoutParams.height = getBottomSheetDialogDefaultHeight();
        layoutParams.width = getBottomSheetDialogDefaultWidth();
        bottomSheet.setLayoutParams(layoutParams);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
    private int getBottomSheetDialogDefaultHeight() {
        return getWindowHeight() * 83 / 100;
    }
    private int getWindowHeight() {
        // Calculate window height for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
    private int getBottomSheetDialogDefaultWidth() {
        return getWindowWidth() * 90 / 100;
    }
    private int getWindowWidth() {
        // Calculate window width for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    @Override
    public void onSave(File filePath) {
        Log.d(TAG, "onSave()");
        mMediaScannerClient = new MediaScannerConnection.MediaScannerConnectionClient() {
            @Override
            public void onMediaScannerConnected() {
                mediaScannerConnection.scanFile(filePath.getPath(),null);
                System.out.println("미디어 스캔 성공");
            }
            @Override
            public void onScanCompleted(String path, Uri uri) {
                System.out.println("미디어 스캔 연결 종료 처리 uri="+uri);
                mediaScannerConnection.disconnect();
            }
        };
        mediaScannerConnection = new MediaScannerConnection((Activity) getContext(),mMediaScannerClient);
        mediaScannerConnection.connect();
    }
}
