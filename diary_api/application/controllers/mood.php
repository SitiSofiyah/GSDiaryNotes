<?php
require ('application/libraries/REST_Controller.php');

class mood extends REST_Controller {

   private $folder_upload = 'uploads/';

    function all_get(){
        $get_mood = $this->db->query("SELECT * FROM mood")->result();
       $this->response(
           array(
               "status" => "success",
               "result" => $get_mood
           )
       );
    }

    function all_post() {
        $action  = $this->post('action');
        $mood = array(
                       'id_mood' => $this->post('id_mood'),
                       'mood'      => $this->post('mood'),
                   );

        switch ($action) {
            case 'insert':
                $this->insertMood($mood);
                break;
            
            
            case 'delete':
                $this->deleteMood($mood);
                break;
            
            default:
                $this->response(
                    array(
                        "status"  =>"failed",
                        "message" => "action harus diisi"
                    )
                );
                break;
        }
    }
 function insertMood($mood){
    
           $mood['mood'] = $this->uploadMood();

           $do_insert = $this->db->insert('mood', $mood);
          
           if ($do_insert){
               $this->response(
                   array(
                       "status" => "success",
                       "result" => array($mood),
                       "message" => $do_insert
                   )
               );
            }
       }

    function deleteMood($mood){

        if (empty($mood['id_mood'])){
           $this->response(
               array(
                   "status" => "failed",
                   "message" => "ID mood harus diisi"
               )
           );
       } else {
           // Cek apakah ada di database
           $get_mood_baseID =$this->db->query("
               SELECT 1
               FROM mood
               WHERE id_mood = {$mood['id_mood']}")->num_rows();

           if($get_mood_baseID > 0){
               
               $get_mood_url =$this->db->query("
               SELECT mood
               FROM mood
               WHERE id_mood = {$mood['id_mood']}")->result();
           
                if(!empty($get_mood_url)){

                    // Dapatkan nama file
                    $photo_nama_file = basename($get_mood_url[0]->mood);
                    // Dapatkan letak file di folder upload
                    $photo_lokasi_file = realpath(FCPATH . $this->folder_upload . $photo_nama_file);
                    
                    // Jika file ada, hapus
                    if(file_exists($photo_lokasi_file)) {
                        // Hapus file
                       unlink($photo_lokasi_file);
                   }

                    $this->db->query("
                       DELETE FROM mood
                       WHERE id_mood = {$mood['id_mood']}");
                   $this->response(
                       array(
                           "status" => "success",
                           "message" => "Data ID = " .$mood['id_mood']. " berhasil dihapus"
                       )
                   );
               }
           
            } else {
                $this->response(
                    array(
                        "status" => "failed",
                        "message" => "ID mood tidak ditemukan"
                    )
                );
            }
       }
    }

    function uploadMood() {

        // Apakah user upload gambar?
        if ( isset($_FILES['mood']) && $_FILES['mood']['size'] > 0 ){

            // Foto disimpan di android-api/uploads
            $config['upload_path'] = realpath(FCPATH . $this->folder_upload);
            $config['allowed_types'] = 'jpeg|jpg|png';

           // Load library upload & helper
           $this->load->library('upload', $config);
           $this->load->helper('url');

           // Apakah file berhasil diupload?
           if ( $this->upload->do_upload('mood')) {

               // Berhasil, simpan nama file-nya
               // URL image yang disimpan adalah http://localhost/android-api/uploads/namafile
               $img_data = $this->upload->data();
               $post_image = $this->folder_upload.$img_data['file_name'];

           } else {

               // Upload gagal, beri nama image dengan errornya
               // Ini bodoh, tapi efektif
               $post_image = $this->upload->display_errors();
               
           }
       } else {
           // Tidak ada file yang di-upload, kosongkan nama image-nya
           $post_image = '';
       }

       return $post_image;
    }
  }

