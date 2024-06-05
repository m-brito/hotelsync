package br.edu.ifsp.hotelsync.domain.usecases;

import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.persistence.dao.ProductDao;
import br.edu.ifsp.hotelsync.domain.usecases.product.create.CreateProductUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.product.create.CreateProductUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.product.find.FindOneProductUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.product.find.FindOneProductUseCaseImpl;
import br.edu.ifsp.hotelsync.domain.usecases.product.update.UpdateProductUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.product.update.UpdateProductUseCaseImpl;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductUseCaseImplTest {

    @Mock
    ProductDao repository;

    Faker faker = new Faker();

    @Nested
    @DisplayName("Create Product Use Case")
    class CreateProductTest {

        private CreateProductUseCase sut;

        @BeforeEach
        void setUp(){
            sut = new CreateProductUseCaseImpl(repository);
        }

        @Test
        @DisplayName("when successfully creating a product")
        public void createValid(){
            CreateProductUseCase.RequestModel request = new CreateProductUseCase.RequestModel(
                    "Product Description",
                    10.0
            );

            sut.createProduct(request);
            verify(repository).save(any());
        }

        @Test
        @DisplayName("when failing to create a product")
        public void createInvalid(){
            CreateProductUseCase.RequestModel request = new CreateProductUseCase.RequestModel(
                    "",
                    -10.0
            );

            assertThrows(IllegalArgumentException.class, () -> sut.createProduct(request));
        }

    }

    @Nested
    @DisplayName("Find Product Use Cases")
    public class FindProductTest{
        private FindOneProductUseCase sut;

        @BeforeEach
        public void setUp(){
            sut = new FindOneProductUseCaseImpl(repository);
        }

        @Test
        @DisplayName("should successfully find a product")
        public void findValid(){
            long id = 10L;
            when(repository.findOneByKey(id)).thenReturn(createProduct(id));

            assertDoesNotThrow(() -> sut.findOneById(new FindOneProductUseCase.RequestModel(id)));
        }

        @Test
        @DisplayName("should fail to find non existent product")
        public void findInvalid(){
            long id = 10L;
            when(repository.findOneByKey(id)).thenReturn(Optional.empty());

            assertThrows(NoSuchElementException.class, () -> sut.findOneById(new FindOneProductUseCase.RequestModel(id)));
        }
    }

    private Optional<Product> createProduct(Long id){
        try {
            return Optional.of(
                    Product.createProductWithId(
                            id,
                            faker.lorem().sentence(),
                            faker.number().randomDouble(2, 1, 100)
                    ));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Nested
    @DisplayName("Update Product Use Cases")
    public class UpdateProductTest {
        private UpdateProductUseCase sut;

        @BeforeEach
        public void setUp() {
            sut = new UpdateProductUseCaseImpl(repository);
        }

        @Test
        @DisplayName("when successfully updating a product")
        public void updateValid() {
            long id = 10L;
            Optional<Product> optionalProduct = createProduct(id);
            if (optionalProduct.isPresent()) {
                when(repository.existsByKey(id)).thenReturn(true);

                UpdateProductUseCase.RequestModel request = new UpdateProductUseCase.RequestModel(
                        id,
                        "Updated Product Description",
                        20.0
                );

                sut.updateProduct(request);
                verify(repository).update(any());
            }
        }

        @Test
        @DisplayName("when failing to update a product")
        public void updateInvalid() {
            long id = 10L;

            UpdateProductUseCase.RequestModel request = new UpdateProductUseCase.RequestModel(
                    id,
                    "",
                    -20.0
            );

            assertThrows(NoSuchElementException.class, () -> sut.updateProduct(request));
            verify(repository, never()).update(any());
        }
    }
}