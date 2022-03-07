<?php
namespace App\Entity;
class PropertySearch
{
    private $name;
    public function getNom(): ?string
    {
        return $this->name;
    }
    public function setNom(string $name): self
    {
        $this->name = $name;
        return $this;
    }
}