<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\ModifType;
use App\Form\RegistrationType;
use Doctrine\Persistence\ObjectManager;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

    
    /**
      * @Route("/admin")
      */
     
class AdminController extends AbstractController
{
   /**
     * @Route("/ajoutad", name="AjoutAd")
     */
    public function Registration( Request $request , ObjectManager $manager, 
    UserPasswordEncoderInterface $encoder )
    {
        $User = new User; 

        $form = $this->createForm (RegistrationType::class, $User);
        $form->handleRequest($request);

        if ($form-> isSubmitted()&& $form-> isValid()){
            $hash = $encoder->encodePassword($User , $User->getPassword());
             $User->setPassword($hash);
            $manager->persist($User);
            $manager->flush($User);
            return $this->redirectToRoute('login');
        }

        Return $this-> render ('back_pages\ajoutad.html.twig',[
            'form' => $form->createview()]) ;     
    } 

   /**
     * @Route("/modifad", name="modifad")
     */
    public function editad( Request $request )
    {
        
        $User = $this->getUser();
        $form = $this->createForm (ModifType::class, $this->getUser() );
        $form->handleRequest($request);

        if ($form-> isSubmitted()&& $form-> isValid()){
            $em = $this->getDoctrine()->getManager()  ;
            $em->persist($User);
            $em->flush($User);

            $this -> addFlash('message', 'profil mis à jour') ;
            return $this->redirectToRoute('modifad');
        }

        Return $this-> render ('back_pages\userprofile.html.twig',[
            'form' => $form->createview()]) ; 
       
        
    } 

    
    

     
}
