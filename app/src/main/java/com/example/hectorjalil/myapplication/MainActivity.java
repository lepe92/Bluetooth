package com.example.hectorjalil.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> itemsAdapter;
    BluetoothAdapter mB;

    private static final int REQUEST_ENABLE_BT = 1;
    private ArrayList<BluetoothDevice> btDeviceList = new ArrayList<BluetoothDevice>();
    ListView ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ls = (ListView) findViewById(R.id.listView);
      //  Button bt= (Button) findViewById(R.id.button);

        itemsAdapter=        new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
      mB= BluetoothAdapter.getDefaultAdapter();
        if(mB==null){
            Toast.makeText(getApplicationContext(), "No tiene bluetooth",Toast.LENGTH_LONG).show();
        }
        else{    Toast.makeText(getApplicationContext(), "Si tiene bluetooth\nNombre: "+mB.getName()+"\nDireccion: "+mB.getAddress(),Toast.LENGTH_LONG).show();
            if(mB.isEnabled()==true)
                Toast.makeText(getApplicationContext(), "Bluetooth activado",Toast.LENGTH_LONG).show();
            else {Toast.makeText(getApplicationContext(), "Bluetooth desactivado, pero se activara en breve",Toast.LENGTH_LONG).show();

                if(mB.enable()==true)
                    Toast.makeText(getApplicationContext(), "Bluetooth activado",Toast.LENGTH_LONG).show();
            }
//una vez activado, buscamos dispositivios conocidos

           /* Set<BluetoothDevice> pairedDevices = mB.getBondedDevices();
// If there are paired devices
            if (pairedDevices.size() > 0) {
                // Loop through paired devices
                for (BluetoothDevice device : pairedDevices) {
                    // Add the name and address to an array adapter to show in a ListView
                    itemsAdapter.add(device.getName() + "\n" + device.getAddress());
                    Toast.makeText(getApplicationContext(),device.getName(),Toast.LENGTH_LONG).show();
                }
                ls.setAdapter(itemsAdapter);
            }*/

        }

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothDevice.ACTION_UUID);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(ActionFoundReceiver, filter); // Don't forget to unregister during onDestroy

        /*bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //mB.startDiscovery();
           //     btDeviceList.clear();
            //    itemsAdapter.clear();
                CheckBTState();
                for(int i=0; i<btDeviceList.size();i++){
                    itemsAdapter.add(btDeviceList.get(i).getName()+"|"+btDeviceList.get(i).getAddress());
                }
                ls.setAdapter(itemsAdapter);
             //   Toast.makeText(getApplicationContext(),btDeviceList.size(),Toast.LENGTH_LONG).show();
            }
        }); */

        CheckBTState();

///////////////////////hacer visible el dispositivo a los demás
      /*  Intent discoverableIntent = new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
*/
    }

    /* This routine is called when an activity completes.*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            CheckBTState();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mB != null) {
            mB.cancelDiscovery();
        }
        unregisterReceiver(ActionFoundReceiver);
    }

    private void CheckBTState() {
        // Check for Bluetooth support and then check to make sure it is turned on
        // If it isn't request to turn it on
        // List paired devices
        // Emulator doesn't support Bluetooth and will return null
        if(mB==null) {
          //  out.append("\nBluetooth NOT supported. Aborting.");
            return;
        } else {
            if (mB.isEnabled()) {
             //   out.append("\nBluetooth is enabled...");

                // Starting the device discovery
                mB.startDiscovery();
            } else {
                Intent enableBtIntent = new Intent(mB.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    private final BroadcastReceiver ActionFoundReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //Toast.makeText(getApplicationContext(),btDeviceList.size(),Toast.LENGTH_LONG).show();
                Log.i("Detectado",device.getName());
                //  out.append("\n  Device: " + device.getName() + ", " + device);
                btDeviceList.add(device);
                    itemsAdapter.add(device.getName()+"|"+device.getAddress());
                ls.setAdapter(itemsAdapter);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
