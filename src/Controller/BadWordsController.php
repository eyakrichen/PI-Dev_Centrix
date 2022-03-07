<?php

namespace App\Controller;

use App\Entity\BadWords;
use App\Form\BadWordsType;
use App\Repository\BadWordsRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/badwords")
 */
class BadWordsController extends AbstractController
{
    /**
     * @Route("/", name="app_bad_words_index", methods={"GET"})
     */
    public function index(BadWordsRepository $badWordsRepository): Response
    {
        return $this->render('bad_words/index.html.twig', [
            'bad_words' => $badWordsRepository->findAll(),
        ]);
    }
   

    /**
     * @Route("/new", name="app_bad_words_new", methods={"GET", "POST"})
     */
    public function new(Request $request, BadWordsRepository $badWordsRepository): Response
    {
        $badWord = new BadWords();
        $form = $this->createForm(BadWordsType::class, $badWord);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $badWordsRepository->add($badWord);
            return $this->redirectToRoute('app_bad_words_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('bad_words/new.html.twig', [
            'bad_word' => $badWord,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_bad_words_show", methods={"GET"})
     */
    public function show(BadWords $badWord): Response
    {
        return $this->render('bad_words/show.html.twig', [
            'bad_word' => $badWord,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_bad_words_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, BadWords $badWord, BadWordsRepository $badWordsRepository): Response
    {
        $form = $this->createForm(BadWordsType::class, $badWord);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $badWordsRepository->add($badWord);
            return $this->redirectToRoute('app_bad_words_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('bad_words/edit.html.twig', [
            'bad_word' => $badWord,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_bad_words_delete", methods={"POST"})
     */
    public function delete(Request $request, BadWords $badWord, BadWordsRepository $badWordsRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$badWord->getId(), $request->request->get('_token'))) {
            $badWordsRepository->remove($badWord);
        }

        return $this->redirectToRoute('app_bad_words_index', [], Response::HTTP_SEE_OTHER);
    }
}
