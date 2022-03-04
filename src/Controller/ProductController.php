<?php

namespace App\Controller;

use App\Entity\Product;
use App\Form\ProductType;
use App\Repository\CategoryRepository;
use App\Repository\ProductRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
use Symfony\Component\HttpFoundation\JsonResponse;


/**
 * @Route("/product")
 */
class ProductController extends AbstractController
{
    /**
     * @Route("/getproduct", name="getproduct")
     */
    public function getProdDetailsJsonAction()
    {

        $produit=$this->getDoctrine()->getManager()
            ->getRepository(Product::class)
            ->findAll();

        $data =  array();
        foreach ($produit as $key => $p){
            //$data[$key]['ref_p']= $p->getRefP();
            $data[$key]['idProduit']= $p->getid();
            $data[$key]['nom_produit']= $p->getname();
            $data[$key]['prix']= $p->getprice();
            //echo $data[$key]['nomP'];
            $data[$key]['description']= $p->getdescription();
            //$data[$key]['path']= $p->getPath();

        }

        return new JsonResponse($data);

    }
    /**
     * @Route("/", name="product_index", methods={"GET"})
     */
    public function index(ProductRepository $productRepository): Response
    {
        return $this->render('product/index.html.twig', [
            'products' => $productRepository->findAll(),
        ]);
    }

    /**
     * @Route("/imprime", name="imprimeproduct")
     */
    public function imprimeproduct(ProductRepository $productRepository): Response

    {
        //Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        $products = $productRepository->findAll();

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('product/imprimeproduct.html.twig', [
            'products' => $products,
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Set up the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (inline view)
        $dompdf->stream("Liste_product.pdf", [
            "Attachment" => true
        ]);
        return $this->redirectToRoute('product_index');
    }

    /**
     * @Route("/new", name="product_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $product = new Product();
        $form = $this->createForm(ProductType::class, $product);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $request->files->get('product')['image'];

            $uploads_directory = $this->getParameter('uploads_directory');
            $file_name = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move(
                $uploads_directory,
                $file_name
            );
            $product->setImage($file_name);


            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($product);
            $entityManager->flush();

            return $this->redirectToRoute('product_back_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('product/new.html.twig', [
            'product' => $product,
            'form' => $form->createView(),

        ]);
    }

    /**
     * @Route("/{id}", name="product_show", methods={"GET"})
     */
    public function show(Product $product, CategoryRepository $categoryRepository): Response
    {
        return $this->render('product/show.html.twig', [
            'product' => $product,
            'categories' => $categoryRepository->findAll(),
        ]);
    }

    /**
     * @Route("/{id}/edit", name="product_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Product $product): Response
    {
        $form = $this->createForm(ProductType::class, $product);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $file = $request->files->get('product')['image'];

            $uploads_directory = $this->getParameter('uploads_directory');
            $file_name = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move(
                $uploads_directory,
                $file_name
            );
            $product->setImage($file_name);
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('product_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('product/edit.html.twig', [
            'product' => $product,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="product_delete", methods={"POST"})
     */
    public function delete(Request $request, Product $product): Response
    {
        if ($this->isCsrfTokenValid('delete' . $product->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($product);
            $entityManager->flush();
        }

        return $this->redirectToRoute('product_index', [], Response::HTTP_SEE_OTHER);
    }

}
