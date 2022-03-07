<?php

namespace App\Controller;

use App\Entity\User;

use App\Form\ModifType;
use App\Entity\Contactus;
use App\Form\ContactType;
use App\Form\RegistrationType;
use App\Repository\BadWordsRepository;
use Doctrine\Persistence\ObjectManager;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

   
     /**
      * @Route("/user")
      */
class UserController extends AbstractController
{

    /**
     * @Route("/inscription", name="inscri")
     */
    public function Registration( Request $request , ObjectManager $manager, 
    UserPasswordEncoderInterface $encoder )
    {
        $User = new User; 

        $form = $this->createForm (RegistrationType::class, $User);
        $form->handleRequest($request);

        if ($form-> isSubmitted()&& $form-> isValid()){
          $image = $form->get('image')->getData();
          if($image){
            $originalFileName = pathinfo($image->getClientOriginalName(), PATHINFO_FILENAME);
            $newFileName = $originalFileName.'-'.uniqid().'.'.$image->guessExtension();
            try{
              $image->move($this->getParameter('images_directory'),$newFileName);
            }catch(FileException $e){

            }
            $User->setImage($newFileName);
          }
            $hash = $encoder->encodePassword( $User , $User->getPassword());
             $User->setPassword($hash);
            $manager->persist($User);
            $manager->flush($User);
            
          /* $email = (new TemplatedEmail())
            ->from(new Address('mahrez.fares@esprit.tn', 'RESETPASSWORD'))
            ->to($User->getEmail())
            ->subject('Your password reset request')
            ->htmlTemplate('reset_password/email.html.twig')
        ;*/
            return $this->redirectToRoute('login');
        }

        Return $this-> render ('front_pages/security/registration.html.twig',[
            'form' => $form->createview()]) ; 
       
        
    } 

      /**
     * @Route("/login", name="login")
     */

    public function login()
    {
      return $this->render('front_pages/index.html.twig') ;  
    }

    /**
     * @Route("/logout", name="logout")
     */

    public function logout() {}

    /**
     * @Route("/modif", name="modif")
     */
    public function edit( Request $request )
    {
        
        $User = $this->getUser();
        $form = $this->createForm (ModifType::class, $this->getUser() );
        $form->handleRequest($request);

        if ($form-> isSubmitted()&& $form-> isValid()){
            $em = $this->getDoctrine()->getManager()  ;
            $em->persist($User);
            $em->flush($User);

            $this -> addFlash('message', 'profil mis à jour') ;
            return $this->redirectToRoute('login');
        }

        Return $this-> render ('front_pages/security/edit_profil.html.twig',[
            'form' => $form->createview()]) ; 
       
        
    } 
     
      /**
     * @Route("/contact/failed", name="contact_failed")
     */
    public function index(): Response
    {
        return $this->render('front_pages\security\contactfailed.html.twig');
    }
     /**
     * @Route("/contact", name="contact")
     */
    public function Contact( Request $request , ObjectManager $manager, 
     BadWordsRepository $bwr) : Response
    {
      $allowed = true;
      $Contactus = new Contactus; 

      $form = $this->createForm (ContactType::class, $Contactus);
      $form->handleRequest($request);

      if ($form-> isSubmitted() && $form-> isValid()){
          $badwords = $bwr->findAll();
          foreach($badwords as $word){
            if(strpos($form->get('content')->getData() , $word->getWord()) !== false){
              $allowed = false;
            }
          }
          if($allowed){
            $manager->persist($Contactus);
            $manager->flush();
            return $this->redirectToRoute('contact');
          }else{
            return $this->redirectToRoute('contact_failed');
          }
          
          

      }

        Return $this-> render ('front_pages\security\contact.html.twig',[
            'form' => $form->createview()]) ; 
       
        
    } 
    
    /**
     * @Route("/loginad", name="login")
     */  
    public function loginad()
    {
      return $this->render('back_pages\login.html.twig') ;  
    }

}
