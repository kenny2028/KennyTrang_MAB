//
//  ViewController.swift
//  ktMAB_OMI
//
//  Created by Kenny Trang on 10/1/21.
//

import UIKit





class ViewController: UIViewController,UITextFieldDelegate,UIImagePickerControllerDelegate, UINavigationControllerDelegate,UITableViewDelegate,UITableViewDataSource{


    
    
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
          var finalString : String
//
        
        finalString = calcSlider(slidervalue: slider.value)
        
        
        ratingLabel.text = "Rating - " + finalString
    }
    
    
//    Calculate Slider val and return string
    func calcSlider(slidervalue: Float) -> String {
        var sliderval : Float!
        var finalString : String!
        sliderval = round(slidervalue*10)/10
        if sliderval!.truncatingRemainder(dividingBy: Float(floor(Double(sliderval!)))) == 0 {
            finalString = String(format: "%.0f", sliderval!)
        } else if sliderval == 0 {
            finalString = "0"
        } else {
            finalString = String(sliderval)
        }
        return finalString!
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
//        Add Review to the reviewlist array struct
        reviews.append(reviewlist(titleName: titleField.text!, review: descriptionField.text!, ratingNum: calcSlider(slidervalue: slider.value)))
        print("New review Added! p1 ")
        numReview += 1
        print(numReview)
        
//      Show Image again and remove image and empty text fields
        startingImage.isHidden = false
        titleField.text = ""
        descriptionField.text = ""
        slider.value = 0
        imageView.image = nil
        ratingLabel.text = "Rating - 0"
        print("New review Added! p2")
        tableView.reloadData()
       }
        
        
        

      
    
        
  
        
        
        
        
        
    }
    
    
//Table View -------------------------------------
//    Adapted from https://www.youtube.com/watch?v=WK5vrOD1zCQ
    @IBOutlet var tableView:UITableView!
    var titles = [String]()
    let test = ["String1", "String2"]
    
    // Creating an class object for the reviewer -------------------
    var reviews : [reviewlist] = [];
    var numReview = 0;
//    var newReview = reviewlist(titleName: "EXAMPLE TITLE", review: "Please start by adding a review above!", ratingNum: "10")
    













    //--------------------------------------------------------------
    
//Table View Functions --------------------------
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        Returns the amount of entrys within
//        reviews.append(newReview)
        return numReview
    }
    
    
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        grab cell property of first default cell
        let cell = tableView.dequeueReusableCell(withIdentifier: "TableViewCell", for: indexPath) as! TableViewCell
//        for i in 0...numReview-1 {
////            cell.titleLabel.text = test[indexPath.row]
//            cell.titleLabel.text = reviews[i].title! + " - " + reviews[i].rating!
//            cell.cellImage.backgroundColor = .red
//            cell.descriptionLabel.text = reviews[i].reviewDescription
//        }
        cell.titleLabel.text = reviews[indexPath.row].title! + " - " + reviews[indexPath.row].rating!
        cell.cellImage.backgroundColor = .red
        cell.descriptionLabel.text = reviews[indexPath.row].reviewDescription
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





















