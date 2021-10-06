//
//  ViewController.swift
//  ktMAB_OMI
//
//  Created by Kenny Trang on 10/1/21.
//

import UIKit



class ViewController: UIViewController,UITextFieldDelegate,UIImagePickerControllerDelegate, UINavigationControllerDelegate,UITableViewDelegate,UITableViewDataSource {
    
    
    
//Text Field Variables ----------------
    @IBOutlet weak var titleField: UITextField!
    @IBOutlet weak var descriptionField: UITextField!
    
//    If return has been pressed on keyboard, return screen to original state
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
//---------------------------------------
    override func viewDidLoad() {
        titleField.delegate = self
        descriptionField.delegate = self
        
        tableView.delegate = self
        tableView.dataSource = self
        
        
//        Register xib so that the custom cell would be in use
        let nib = UINib(nibName: "TableViewCell", bundle: nil)
        tableView.register(nib, forCellReuseIdentifier: "TableViewCell")
        
        super.viewDidLoad()
//        If USER CLICKS OFF SCREEN, REMOVE KEYBOARD
        self.view.addGestureRecognizer(UITapGestureRecognizer(target: self.view, action: #selector(UIView.endEditing(_:))))
        
        // Do any additional setup after loading the view.
    }
    
    
    
//    Update Slider & Text label ------------------
    @IBOutlet weak var ratingLabel: UILabel!
    @IBOutlet weak var slider: UISlider!
    
    @IBAction func ratingSlider(_ sender: UISlider) {
//        if value changes update text label to current value
        var sliderval : Float
        var finalString : String
        sliderval = round(slider.value*10)/10
        if sliderval.truncatingRemainder(dividingBy: Float(floor(Double(sliderval)))) == 0 {
            finalString = String(format: "%.0f", sliderval)
        } else if sliderval == 0 {
            finalString = "0"
        } else {
            finalString = String(sliderval)
        }
        
        ratingLabel.text = "Rating - " + finalString
    }
    
// -----------------------------------------------
    
    
//Upload Image Button and Picture ----------------
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var startingImage: UIImageView!
    

    @IBAction func uploadPressed(_ sender: UIButton) {
        let vc = UIImagePickerController()
        vc.sourceType = .photoLibrary
        vc.delegate = self
        vc.allowsEditing = true
        present(vc, animated: true)
        
        
    }
    
    
//------------------------------------------------


//Add Review -------------------------------------
//    Check for empty field / empty picture
//    Will return pop up
//    Checks for title, than image, than pic, than will let you execute if all is met
    @IBAction func reviewPressed(_ sender: UIButton) {
        
        if titleField.text == "" {
//            title field empty send alert
            let alert = UIAlertController(title: "Warning: Missing Entry", message: "Please Enter a Title", preferredStyle: .alert)

            alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
            self.present(alert, animated: true)
        }
        
       else if descriptionField.text == "" {
//            Description field is empty send alert
            let alert = UIAlertController(title: "Warning: Missing Entry", message: "Please Enter a Review Description", preferredStyle: .alert)

            alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
            self.present(alert, animated: true)
        }
        
       else if startingImage.isHidden == false {
        let alert = UIAlertController(title: "Warning: Missing Entry", message: "Please Add an Image", preferredStyle: .alert)

        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
        self.present(alert, animated: true)
       }
       
       else{
//       No Errors, Entrys Proceed Here
       }
        
        
        

      
    
        
  
        
        
        
        
        
    }
    
    
//Table View -------------------------------------
//    Adapted from https://www.youtube.com/watch?v=WK5vrOD1zCQ
    @IBOutlet var tableView:UITableView!
    var titles = [String]()
    let test = ["String1", "String2"]
    
//Table View Functions --------------------------
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        Returns the amount of entrys within
        return test.count
    }
    
    
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        grab cell property of first default cell
        let cell = tableView.dequeueReusableCell(withIdentifier: "TableViewCell", for: indexPath) as! TableViewCell
        cell.titleLabel.text = test[indexPath.row]
        cell.cellImage.backgroundColor = .red
        cell.descriptionLabel.text = "Lorem ipsum dolor sit amet, te voluptua disputationi comprehensam nam, sit et eligendi quaestio liberavisse, at odio solum nominati his. Et dolor principes adolescens vis, mel ex mutat meliore. Has cu option vivendum forensibus, eam lorem molestie ex, in ludus meliore ius. Eum ea scribentur delicatissimi, labore propriae no ius. Ea malis facer conceptam pro, corpora democritum at quo, sed an amet omnis diceret. Movet dictas repudiare eu duo, qui tota saepe saperet ea, no eius salutatus assueverit vix. Vel ei gubergren hendrerit maiestatis, prompta omnesque insolens eu qui."
        self.tableView.rowHeight = 130;
        return cell
    }
    
// -----------------------------------------------
    
    
    
    
    
    
    
//------------------------------------------------
// Image picker functions learned and adapted from
// https://www.youtube.com/watch?v=yggOGEzueFk
    
    
    
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        
        if let image = info[UIImagePickerController.InfoKey(rawValue: "UIImagePickerControllerEditedImage")] as? UIImage {
            imageView.image = image
            startingImage.isHidden = true
        }
    

        
        
        picker.dismiss(animated: true, completion: nil)
    }
    
//    Called when cancel
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        picker.dismiss(animated: true, completion: nil)
    }
//------------------------------------------------
    
    
//Custom Cell Functions -------------------------
    
    
    
    

    
// ---------------------------------------------
    
    
    
    
    
    
    
}





















