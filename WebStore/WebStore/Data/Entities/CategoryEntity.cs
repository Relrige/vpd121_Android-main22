﻿using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using WebStore.Data.Entities.Identity;

namespace WebStore.Data.Entities
{
    [Table("tblCategories")]
    public class CategoryEntity : BaseEntity<int>
    {
        [Required, StringLength(255)]
        public string Name { get; set; }
        [Required, StringLength(255)]
        public string Image { get; set; }
        [StringLength(4000)]
        public string Description { get; set; }


        [ForeignKey("User")]
        public int UserId { get; set; }
        public virtual UserEntity User { get; set; }
    }
}