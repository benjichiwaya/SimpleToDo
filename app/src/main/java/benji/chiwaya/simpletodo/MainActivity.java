package benji.chiwaya.simpletodo;

import android.os.Bundle;
import org.apache.commons.io.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private List<String> items = new ArrayList<>();

    String TAG = "MainActivity.class";
    Button button;
    EditText editText;
    RecyclerView recyclerView;
    ItemsAdapter itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.buttonAdd);
        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.recyclerItems);

        loadItems();

        ItemsAdapter.OnlongClickListener onlongClickListener = new ItemsAdapter.OnlongClickListener() {
            @Override
            public void onItemLongClicked(int position){
                //delete the item from the model
                items.remove(position);
                //notify the adapter
                itemsAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), R.string.success_message_removing,Toast.LENGTH_SHORT).show();
                saveItems();
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItems = editText.getText().toString();
                items.add(todoItems);
                itemsAdapter.notifyItemInserted(items.size() - 1);
                editText.setText("");
                Toast.makeText(getApplicationContext(), R.string.success_message_adding,Toast.LENGTH_SHORT).show();
                saveItems();
            }
        });

        itemsAdapter = new ItemsAdapter(items, onlongClickListener);
        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    private File getDataFile(){
        return new File(getFilesDir(),getString(R.string.storage_file_name));
    }

    //load items by reading every line of the data file
    private void loadItems(){
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e(TAG,getString(R.string.error_reading), e);
            items = new ArrayList<>();
        }
    }

    //saves item by writing them into the file
    private void saveItems(){
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch (IOException e) {
            Log.e(TAG,getString(R.string.error_writing),e);
        }
    }
}