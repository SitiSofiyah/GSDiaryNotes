<?php
require('application/libraries/REST_Controller.php');
require APPPATH . 'libraries/Format.php';

class Lokasi extends REST_Controller {

   // show data pembelian
   function index_get() {
       
        $get_transaksi = $this->db->query("SELECT * FROM lokasi")->result();
     
       $this->response(array("status"=>"success","result" => $get_transaksi));
   }

   // insert pembelian
   function index_post() {
       $data_lokasi = array(
           'id_lokasi'     => $this->post('id_lokasi'),
           'lokasi'   => $this->post('lokasi')
           );
          
           //jika id_pembelian tidak ada dalam database maka eksekusi insert
           if (empty($getId)){
                    if (empty($data_lokasi['id_lokasi'])){
                       $this->response(array('status'=>'fail',"message"=>"id_lokasi kosong"));
                    }
                    else if(empty($data_lokasi['lokasi'])){
                       $this->response(array('status'=>'fail',"message"=>"lokasi kosong"));
                    }
                    else{
                       
                           $insert= $this->db->insert('lokasi',$data_lokasi);
                           if ($insert){
                               $this->response(array('status'=>'success','result' => $data_lokasi,"message"=>$insert));   
                           
                          
                       }else{
                           $this->response(array('status'=>'fail',"message"=>$message));   
                       }    
           }  
       }
     }
   

   // update data pembelian
   function index_put() {
       $data_lokasi = array(
                    'id_lokasi'     => $this->put('id_lokasi'),
           'lokasi'   => $this->put('lokasi')
                   );
       
          
           
               //jika masuk disini maka dipastikan id_pembelian ada dalam database
                if (empty($data_lokasi['id_lokasi'])){
                   $this->response(array('status'=>'fail',"message"=>"id_lokasi kosong"));
                }
                else if(empty($data_lokasi['lokasi'])){
                       $this->response(array('status'=>'fail',"message"=>"lokasi kosong"));
                    }
                else{
                  
                       $this->db->where('id_lokasi',$data_lokasi['id_lokasi']);
                       $update= $this->db->update('lokasi',$data_lokasi);
                       if ($update){
                           $this->response(array('status'=>'success','result' => $data_lokasi,"message"=>$update));
                       
                      
                   }else{
                       $this->response(array('status'=>'fail',"message"=>$message));   
                   }
                }
           }

       
   

   // delete pembelian
   function index_delete() {
       $id_lokasi = $this->delete('id_lokasi');
       if (empty($id_lokasi)){
           $this->response(array('status' => 'fail', "message"=>"id_lokasi harus diisi"));
       } else {
           $this->db->where('id_lokasi', $id_lokasi);
           $delete = $this->db->delete('lokasi');  
           if ($this->db->affected_rows()) {
               $this->response(array('status' => 'success','message' =>"Berhasil delete dengan id_lokasi = ".$id_lokasi));
           } else {
               $this->response(array('status' => 'fail', 'message' =>"id_lokasi tidak dalam database"));
           }
       }
   }
}  