using System;
using System.Collections.Generic;

namespace drugstoreAPI.Models
{
    public partial class Product
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Keywords { get; set; }
        public string Description { get; set; }
        public float? Price { get; set; }
    }
}
