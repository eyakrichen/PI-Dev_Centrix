<?php

namespace App\Controller;
use App\Entity\Movie;

use App\Entity\Players;
use App\Entity\PropertySearch;
use App\Form\PlayerSearchType;
use App\Form\PlayersType;
use App\Form\PropertySearchType;
use App\Repository\PlayersRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/players")
 */
class PlayersController extends AbstractController
{

    /**
     *@Route("/",name="article_list")
     */
    public function home(Request $request)
    {
        $propertySearch = new PropertySearch();
        $form = $this->createForm(PropertySearchType::class,$propertySearch);
        $form->handleRequest($request);
        //initialement le tableau des articles est vide,
        //c.a.d on affiche les articles que lorsque l'utilisateur
        //clique sur le bouton rechercher
        $players= [];

        if($form->isSubmitted() && $form->isValid()) {
            //on récupère le nom d'article tapé dans le formulaire
            $name = $propertySearch->getNom();
            if ($name!="")
                //si on a fourni un nom d'article on affiche tous les articles ayant ce nom
                $players= $this->getDoctrine()->getRepository(players::class)->findBy(['name' => $name] );
            else
                //si si aucun nom n'est fourni on affiche tous les articles
                $players= $this->getDoctrine()->getRepository(players::class)->findAll();
        }
        return $this->render('players/index.html.twig',[ 'form' =>$form->createView(), 'players' => $players]);
    }

    /**
     * @Route("/", name="app_players_index", methods={"GET"})
     */
    public function index(PlayersRepository $playersRepository): Response
    {
        return $this->render('players/index.html.twig', [
            'players' => $playersRepository->findAll(),
        ]); }


    /**
     * @Route("/new", name="app_players_new", methods={"GET", "POST"})
     */
    public function new(Request $request, PlayersRepository $playersRepository): Response
    {
        $player = new Players();
        $form = $this->createForm(PlayersType::class, $player);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $playersRepository->add($player);
            return $this->redirectToRoute('app_players_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('players/new.html.twig', [
            'player' => $player,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_players_show", methods={"GET"})
     */
    public function show(Players $player): Response
    {
        return $this->render('players/show.html.twig', [
            'player' => $player,
        ]);
    }



    /**
     * @Route("/{id}/edit", name="app_players_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Players $player, PlayersRepository $playersRepository): Response
    {
        $form = $this->createForm(PlayersType::class, $player);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $playersRepository->add($player);
            return $this->redirectToRoute('app_players_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('players/edit.html.twig', [
            'player' => $player,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_players_delete", methods={"POST"})
     */
    public function delete(Request $request, Players $player, PlayersRepository $playersRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$player->getId(), $request->request->get('_token'))) {
            $playersRepository->remove($player);
        }

        return $this->redirectToRoute('app_players_index', [], Response::HTTP_SEE_OTHER);
    }
}
