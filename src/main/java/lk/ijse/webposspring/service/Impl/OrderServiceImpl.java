package lk.ijse.webposspring.service.Impl;

import lk.ijse.webposspring.repository.CustomerRepository;
import lk.ijse.webposspring.repository.ItemRepository;
import lk.ijse.webposspring.repository.OrderRepository;
import lk.ijse.webposspring.dto.OrderDTO;
import lk.ijse.webposspring.dto.OrderDetailDTO;
import lk.ijse.webposspring.entity.*;
import lk.ijse.webposspring.exception.CustomerNotFoundException;
import lk.ijse.webposspring.exception.DataPersistFailedException;
import lk.ijse.webposspring.exception.ItemNotFoundException;
import lk.ijse.webposspring.exception.OrderNotFoundException;
import lk.ijse.webposspring.service.OrderService;
import lk.ijse.webposspring.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final ItemRepository itemRepository;

    private final Mapping mapping;

    @Override
    public void placeOrder(OrderDTO orderDTO) {
        Optional<Customer> customer = customerRepository.findById(orderDTO.getCustomerId());

        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found");
        }
        Order newOrder = createOrder(orderDTO, customer.get());

        Set<OrderDetail> orderDetails = saveOrderDetailsAndUpdateItems(newOrder, orderDTO.getOrderDetails());
        newOrder.setOrderDetails(orderDetails);

        saveOrder(newOrder);
    }

    private Order createOrder(OrderDTO orderDTO, Customer customer) {
        Order orders = new Order();
        orders.setOrderId(orderDTO.getOrderId());
        orders.setDateAndTime(LocalDateTime.now());
        orders.setCustomer(customer);
        orders.setAmount_payed(orderDTO.getAmount_payed());
        orders.setSubtotal(orderDTO.getSubtotal());
        orders.setDiscount(orderDTO.getDiscount());
        return orders;
    }

    private void saveOrder(Order orders) {
        try {
            orderRepository.save(orders);
        } catch (Exception e) {
            throw new DataPersistFailedException("Failed to save the order");
        }
    }

    private Set<OrderDetail> saveOrderDetailsAndUpdateItems(Order orders, List<OrderDetailDTO> orderDetailDTOList) {
        List<OrderDetail> orderDetailList = orderDetailDTOList.stream().map(orderDetailDto -> {
            Optional<Item> item = itemRepository.findById(orderDetailDto.getItemId());

            if (item.isEmpty()) {
                throw new ItemNotFoundException("Item not found");
            }

            item.get().setQuantity(item.get().getQuantity() - orderDetailDto.getQuantity());

            OrderDetailsId orderDetailsId = new OrderDetailsId(orders.getOrderId(), item.get().getItemId());
            return new OrderDetail(orderDetailsId, orders, item.get(), orderDetailDto.getQuantity(), orderDetailDto.getPrice());
        }).toList();
        return new HashSet<>(orderDetailList);
    }


    @Override
    public OrderDTO searchOrder(String orderId) {
        if (orderRepository.existsById(orderId)) {
            return mapping.convertToOrderDTO(orderRepository.findById(orderId));
        } else {
            throw new OrderNotFoundException("Order Not Found");
        }
    }

    @Override
    public Map<String, String> getOrderId() {
        Optional<Order> lastOrder = orderRepository.findTopByOrderByOrderIdDesc();
        Map<String, String> orderId = new HashMap<>();
        if (lastOrder.isPresent()) {
            String lastOrderId = lastOrder.get().getOrderId();
            String prefix = lastOrderId.substring(0, 1);
            int number = Integer.parseInt(lastOrderId.substring(1));
            number++;
            String formattedNumber = String.format("%03d", number);
            orderId.put("orderId", prefix + formattedNumber);
            return orderId;
        }
        orderId.put("orderId", "O001");
        return orderId;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        return mapping.convertToOrderDTOList(allOrders);
    }
}