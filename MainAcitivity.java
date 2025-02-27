package com.example.qrscannerapp;
import com.journeyapps.barcodescanner.ScanOptions;
import com.journeyapps.barcodescanner.ScanContract;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    private final ActivityResultLauncher<ScanOptions> qrScanner = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            tvResult.setText("QR Code: " + result.getContents());
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnScan = findViewById(R.id.btn_scan);
        tvResult = findViewById(R.id.tv_result);

        btnScan.setOnClickListener(v -> {
            ScanOptions options = new ScanOptions();
            options.setPrompt("Scan a QR Code");
            options.setBeepEnabled(true);
            options.setOrientationLocked(true);
            options.setCaptureActivity(CaptureActivity.class);
            qrScanner.launch(options);
        });
    }
}
