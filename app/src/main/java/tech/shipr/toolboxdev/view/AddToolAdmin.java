package tech.shipr.toolboxdev.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import tech.shipr.toolboxdev.R;
import tech.shipr.toolboxdev.model.Tool;

public class AddToolAdmin extends AppCompatActivity {
    EditText catEditText;
    EditText nameEditText;
    EditText urlEditText;

    String cat;
    String name;
    String url;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tool_admin);
        findEditText();
        initFirebase();
    }

    private void findEditText() {
        nameEditText = findViewById(R.id.nameEditText);
        urlEditText = findViewById(R.id.urlEditText);
        catEditText = findViewById(R.id.catEditText);
    }

    private void initFirebase() {
        db = FirebaseFirestore.getInstance();
    }

    public void submit(View v) {
        getDataFromEditText();
        uploadToolToPrivate(createTool());
    }

    private void getDataFromEditText() {
        cat = catEditText.getText().toString();
        name = nameEditText.getText().toString();
        url = urlEditText.getText().toString();
    }

    private Tool createTool() {
        Tool tool = new Tool();
        tool.setName(name);
        tool.setUrl(url);
        return tool;
    }

    private void uploadToolToPrivate(Tool tool) {
        db.collection("cat").document(cat).collection("products").document(tool.getName()).set(tool);
    }


}